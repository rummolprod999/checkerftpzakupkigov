import java.sql.DriverManager
import java.sql.SQLException
import java.util.*

val UrlConnect: String
    get() = String.format("jdbc:mysql://%s:%d/%s?jdbcCompliantTruncation=false&useUnicode=true&characterEncoding=utf-8&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow&connectTimeout=5000&socketTimeout=30000", "localhost", 3306, "tender")

class RegionTable {
    var id: Int = 0
    var name: String? = null
    var path: String? = null
    var conf: String? = null
    var path223: String? = null
}

@Throws(SQLException::class)
fun getRegions(): ArrayList<RegionTable> {
    val arr = ArrayList<RegionTable>()
    DriverManager.getConnection(UrlConnect, "root", "1234").use { con ->
        val reg = con.prepareStatement("SELECT * FROM region")
        val r = reg.executeQuery()
        while (r.next()) {
            val region = RegionTable()
            region.id = r.getInt("id")
            region.name = r.getString("name")
            region.path = r.getString("path")
            region.conf = r.getString("conf")
            region.path223 = r.getString("path223")
            arr.add(region)
        }
        reg.close()
        return arr
    }
}