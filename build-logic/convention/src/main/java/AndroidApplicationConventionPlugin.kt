import com.android.build.api.dsl.ApplicationExtension
import com.s2start.convention.ExtensionType
import com.s2start.convention.configureBuildTypes
import com.s2start.convention.configureKotlinAndroid
import com.s2start.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure


class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()

                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()
                    buildConfigField("String", "MAPS_API_KEY", "\"AIzaSyCJ6vZIi0CDim1feYIX4BaiJ2rObKQwd0g\"")

                }

                configureKotlinAndroid(this)

                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.APPLICATION
                )
            }
        }
    }
}