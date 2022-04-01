import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.nio.file.Files

import java.nio.file.Paths

open class Publishing : DefaultTask() {
    @TaskAction
    fun publish() {
        val dokkaRoot = Paths.get(project.projectDir.absolutePath, "dokka")

        val moduleFile = Paths.get(project.projectDir.absolutePath, "-modules.html")
        val newIndex = Paths.get(project.projectDir.absolutePath,"index.html")

        Files.copy(moduleFile, newIndex)
    }
}
