import java.io.IOException
import java.lang.Thread.sleep
import java.util.*


@Throws(IOException::class)
private fun ftpLst(pathParse: String): ArrayList<String> {
    val ftpClient = FtpClient.getConnect()
    ftpClient.changeWorkingDirectory(pathParse)
    val s = ArrayList(Arrays.asList(*ftpClient.listNames()))
    ftpClient.abort()
    return s
}

fun getListFtp(pathParse: String): ArrayList<String> {
    var timesleep = 5000L
    var arr = ArrayList<String>()
    var count = 1
    while (true) {
        try {

            arr = ftpLst(pathParse)
            break
        } catch (e: Exception) {
            if (count > 3) {
                println("Не смогли найти директорию после попытки $count, $pathParse, ${e.message}")
                break
            }
            FtpClient.createConnect()
            count++
            timesleep += 5000
            try {
                sleep(timesleep)
            } catch (ignored: InterruptedException) {

            }

        }

    }

    return arr
}

fun sendEmail() {
    if (!list_arr.isEmpty()) {
        val sender = Sender("info@enter-it.ru", "Dft56Point")
        val subj = "Появились новые типы тендеров"
        val text = list_arr.joinToString()
        sender.send(subj, text, "info@enter-it.ru", "rummolprod999@gmail.com")
    }

}