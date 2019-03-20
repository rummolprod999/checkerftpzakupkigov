import java.util.logging.FileHandler
import java.util.logging.Logger
import java.util.logging.SimpleFormatter

lateinit var arg: arguments
lateinit var userftp: String
lateinit var passftp: String
val logger: Logger = Logger.getLogger("LogToFile")

fun main(args: Array<String>) {
    initLogger()
    logger.info("Start program")
    init(args)
    try {
        checker()
        sendEmail()
    } catch (e: Exception) {
        println(e)
    }
    logger.info("End program")
}

fun initLogger() {
    val handler = FileHandler("message_check_ftp.log", true)
    handler.formatter = SimpleFormatter()
    logger.addHandler(handler)
    logger
}

fun init(args: Array<String>) {
    if (args.isEmpty()) {
        println("bad argument, use ${arguments.values().contentToString()}")
        System.exit(1)
    }
    when (args[0]) {
        "A44" -> {
            arg = arguments.A44
            userftp = AuthFtp.login44
            passftp = AuthFtp.pass44
        }
        "A223" -> {
            arg = arguments.A223
            userftp = AuthFtp.login223
            passftp = AuthFtp.pass223
        }
        else -> {
            println("bad argument, use ${arguments.values().contentToString()}")
            System.exit(1)
        }
    }
}