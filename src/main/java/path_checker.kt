fun checker() {
    val reg = getRegions()
    reg.forEach {
        when (arg) {
            arguments.A44 -> {
            }
            arguments.A223 -> {
                ListFtpPath.list223.forEach { a -> getList("/out/published/${it.path223}/$a/daily/") }

            }
        }
    }
}

fun getList(path: String) {
    val result = getListFtp(path).filter { it != "out" }
    if (result.isEmpty()) {
        println("ok")
        return
    }
    result.forEach { println("$path$it") }
}