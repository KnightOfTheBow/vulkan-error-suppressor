import com.electronwill.nightconfig.core.io.IndentStyle
import com.electronwill.nightconfig.toml.TomlParser
import com.electronwill.nightconfig.toml.TomlWriter
import java.io.FilterReader
import java.io.Reader
import java.io.StringReader

class TomlMinifyReader(reader: Reader) : FilterReader(
    StringReader(reader.use { TOML_WRITER.get().writeToString(TOML_PARSER.get().parse(it)).trim() })
) {
    private companion object {
        val TOML_PARSER: ThreadLocal<TomlParser> = ThreadLocal.withInitial(::TomlParser)
        val TOML_WRITER: ThreadLocal<TomlWriter> = ThreadLocal.withInitial {
            TomlWriter().apply {
                setIndent(IndentStyle.NONE)
                setNewline("\n")
                setIndentArrayElementsPredicate { false }
            }
        }
    }
}