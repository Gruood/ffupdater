package de.marmaro.krt.ffupdater.app.impl

import android.os.Build
import de.marmaro.krt.ffupdater.R
import de.marmaro.krt.ffupdater.app.AvailableVersionResult
import de.marmaro.krt.ffupdater.app.BaseAppWithCachedUpdateCheck
import de.marmaro.krt.ffupdater.app.impl.fetch.mozillaci.MozillaCiLogConsumer
import de.marmaro.krt.ffupdater.device.ABI

/**
 * https://firefox-ci-tc.services.mozilla.com/tasks/index/mobile.v2.fenix.release.latest
 * https://www.apkmirror.com/apk/mozilla/firefox/
 */
class FirefoxRelease : BaseAppWithCachedUpdateCheck() {
    override val packageName = "org.mozilla.firefox"
    override val displayTitle = R.string.firefox_release__title
    override val displayDescription = R.string.firefox_release__description
    override val displayWarning: Int? = null
    override val displayDownloadSource = R.string.mozilla_ci
    override val displayIcon = R.mipmap.ic_logo_firefox_release
    override val minApiLevel = Build.VERSION_CODES.LOLLIPOP
    override val supportedAbis = listOf(ABI.ARM64_V8A, ABI.ARMEABI_V7A, ABI.X86_64, ABI.X86)

    @Suppress("SpellCheckingInspection")
    override val signatureHash = "a78b62a5165b4494b2fead9e76a280d22d937fee6251aece599446b2ea319b04"

    override suspend fun updateCheckWithoutCaching(): AvailableVersionResult {
        val abiString = getStringForCurrentAbi(
            "armeabi-v7a", "arm64-v8a", "x86",
            "x86_64"
        )
        val mozillaCiConsumer = MozillaCiLogConsumer(
            task = "mobile.v2.fenix.release.latest.$abiString",
            apkArtifact = "public/build/$abiString/target.apk",
        )
        val result = mozillaCiConsumer.updateCheck()
        return AvailableVersionResult(
            downloadUrl = result.url,
            version = result.version,
            publishDate = result.releaseDate,
            fileSizeBytes = null,
            fileHash = null
        )
    }

    companion object
}