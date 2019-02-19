lateinit var arg: arguments
lateinit var userftp: String
lateinit var passftp: String

fun main(args: Array<String>) {
    init(args)
    try {
        checker()
        sendEmail()
    } catch (e: Exception) {
        println(e)
    }
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