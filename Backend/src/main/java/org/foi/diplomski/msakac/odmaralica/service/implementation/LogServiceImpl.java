package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.get.EncryptedLogGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.LogGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.LogPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.LogPutDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.LogMapper;
import org.foi.diplomski.msakac.odmaralica.model.Log;
import org.foi.diplomski.msakac.odmaralica.repository.LogRepository;
import org.foi.diplomski.msakac.odmaralica.security.AesEncryptionProperties;
import org.foi.diplomski.msakac.odmaralica.service.ILogService;
import org.foi.diplomski.msakac.odmaralica.service.base.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.persistence.EntityManager;
import java.security.Key;

@Service
public class LogServiceImpl extends AbstractBaseService<Log, LogRepository, LogMapper, LogGetDTO, LogPostDTO, LogPutDTO> implements ILogService {

    private final AesEncryptionProperties aesProperties;

    @Autowired
    public LogServiceImpl(LogRepository repository, LogMapper mapper, EntityManager entityManager, AesEncryptionProperties aesProperties) {
        super(repository, mapper, entityManager);
        this.aesProperties = aesProperties;
    }

    @Override
    public Log convertPost(LogPostDTO entityPost) {
        return mapper.convert(entityPost);
    }

    @Override
    public Log convertPut(LogPutDTO entityPut) {
        return mapper.convert(entityPut);
    }

    @Override
    public LogGetDTO convertGet(Log entity) {
        return mapper.convert(entity);
    }

    public Class<Log> getEntityClass() {
        return Log.class;
    }

    public List<EncryptedLogGetDTO> findAllEncrypt() {
        List<Log> entities = repository.findAll();
        List<EncryptedLogGetDTO> getEntities = new ArrayList<>();
        for (Log entity : entities) {
            try {
                String encryptedUser = "";
                String encryptedId = encrypt(entity.getId().toString());
                if(entity.getUser() != null) {
                    encryptedUser = encrypt(entity.getUser().getEmail());
                }
                String encryptedActivityType = encrypt(entity.getActivityType().getName().toString());
                String encryptedLogMessage = encrypt(entity.getLogMessage());
                String encryptedCreatedAt = encrypt(entity.getCreatedAt().toString());
                String encryptedHttpMethod = encrypt(entity.getHttpMethod());
                String encryptedEndpoint = encrypt(entity.getEndpoint());
                String encryptedStatusCode = encrypt(entity.getStatusCode());
                String encryptedIpAddress = encrypt(entity.getIpAddress());
                String encryptedResponseTime = encrypt(entity.getResponseTime());

                EncryptedLogGetDTO encryptedLogEntry = new EncryptedLogGetDTO(encryptedId, encryptedUser, encryptedActivityType, 
                    encryptedLogMessage, encryptedCreatedAt, encryptedHttpMethod, encryptedEndpoint, 
                    encryptedStatusCode, encryptedIpAddress, encryptedResponseTime);
                getEntities.add(encryptedLogEntry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return getEntities;
    }

    public String encrypt(String Data) throws Exception {
        String secretKey = aesProperties.getSecretKey();

        byte[] encoded = Base64.getEncoder().encode(secretKey.getBytes());
        String encodedKey = new String(encoded);

        Key key = generateKey(encodedKey);
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.getEncoder().encodeToString(encVal);
        return encryptedValue;
    }

    private static Key generateKey(String secret) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
        Key key = new SecretKeySpec(decoded, "AES");
        return key;
    }

}
