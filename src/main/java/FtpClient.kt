import org.apache.commons.net.ftp.FTPClient

object FtpClient {
    lateinit var client: FTPClient
    fun getConnect(): FTPClient {
        if (FtpClient::client.isInitialized) {
            return client
        }
        createConnect()
        return client
    }

    fun createConnect() {
        client = FTPClient()
        client.connect("ftp.zakupki.gov.ru", 21)
        client.login(userftp, passftp)
        client.enterLocalPassiveMode()

    }
}