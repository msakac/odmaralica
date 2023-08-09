const fs = require("fs");
const path = require("path");

function generateFiles(names, folderPaths) {
  const fileTypes = ["Controller", "Repository", "Service", "ServiceImpl", "GetDTO", "PostDTO", "PutDto",""];

  names.forEach((name) => {
    folderPaths.forEach((folderPath, index) => {
      const fileType = fileTypes[index] || fileTypes[fileTypes.length - 1];
      const fileName = `${name}${fileType}.java`;
      const filePath = path.join(folderPath, fileName);

      const fileContent = generateFileContent(name, fileType);

      fs.writeFileSync(filePath, fileContent);
      console.log(`Created ${filePath}`);
    });
  });
}

function generateFileContent(name, fileType) {
  const className = `${name}${fileType}`;
  return `public class ${className} {\n    // TODO: Implement ${className}\n}\n`;
}

const names = ["Reservation", "ResidenceAttribute", "Residence", "Region", "City", "Address", "Amenity", "Amount", "PricePeriod", "AccommodationUnit", "ResidenceImage", "AccommodationUnitImage", "Log", "ActivityType"];
const folderPaths = [
    "./src/main/java/org/foi/diplomski/msakac/odmaralica/controller",
    "./src/main/java/org/foi/diplomski/msakac/odmaralica/repository",
    "./src/main/java/org/foi/diplomski/msakac/odmaralica/service/interface",
    "./src/main/java/org/foi/diplomski/msakac/odmaralica/service",
    "./src/main/java/org/foi/diplomski/msakac/odmaralica/dto/get",
    "./src/main/java/org/foi/diplomski/msakac/odmaralica/dto/post",
    "./src/main/java/org/foi/diplomski/msakac/odmaralica/dto/put",
    "./src/main/java/org/foi/diplomski/msakac/odmaralica/model",

];

generateFiles(names, folderPaths);