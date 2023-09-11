@Aspect
@Component
public class LoggingAspect {

  private final
  Logger
  log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private HttpServletRequest
  request;
  @Autowired
  private HttpServletResponse
  response;
  @Autowired
  private LogServiceImpl
  logService;
  @Autowired
  private ActivityTypeServiceImpl
  activityTypeService;

  @Pointcut("within(@org.springframework.stereotype.Repository *)" +
      " || within(@org.springframework.stereotype.Service *)" +
      " || within(@org.springframework.web.bind.annotation.RestController *)")
  public void

  springBeanPointcut() {
  }

  @Pointcut("within(org.foi.diplomski.msakac.odmaralica.controller..*)")
  public void

  applicationPackagePointcut() {
  }

  @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)" +
      " || @annotation(org.springframework.web.bind.annotation.PutMapping)" +
      " || @annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
      " || @annotation(org.springframework.web.bind.annotation.PostMapping)")
  public void

  requestMappingMethods() {
  }

  @AfterThrowing(pointcut = "applicationPackagePointcut() && (springBeanPointcut() || requestMappingMethods())", throwing = "e")
  public void

  logAfterThrowing(JoinPoint

  joinPoint
,
  Throwable
  e
) {
  log
.

  error(

  "Exception in {}.{}() with cause = {}"
,
  joinPoint
.

  getSignature()

.

  getDeclaringTypeName()

,
  joinPoint
.

  getSignature()

.

  getName()

,
  e
.

  getCause()

!=
  null ?
  e
.

  getCause(): "NULL"

);
}

@Around("applicationPackagePointcut() && (springBeanPointcut() || requestMappingMethods())")
public
Object
logAround(ProceedingJoinPoint
joinPoint
)
throws
Throwable
{
  long
  startTime = System.currentTimeMillis();
  String
  className = joinPoint.getSignature().getDeclaringType().getSimpleName();
  String
  methodName = joinPoint.getSignature().getName();
  try {
    ResponseEntity < CreateResponseDTO < Object >> result = (ResponseEntity<CreateResponseDTO<Object>>)
    joinPoint.proceed();
    logData(startTime, className, methodName, result);
    return result;
  } catch (IllegalArgumentException
  e
)
  {
    log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
        className, methodName);
    throw e;
  }
}