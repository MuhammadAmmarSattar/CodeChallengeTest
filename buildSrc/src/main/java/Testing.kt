object Testing {
    private const val junitVersion = "4.13.2"
    private const val espressoVersion = "3.5.1"

    const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"

    const val junit4 = "junit:junit:$junitVersion"

    private const val junitAndroidExtVersion = "1.1.5"
    const val junitAndroidExt = "androidx.test.ext:junit:$junitAndroidExtVersion"

    private const val coroutinesTestVersion = "1.5.1"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion"

    private const val truthVersion = "1.1.3"
    const val truth = "com.google.truth:truth:$truthVersion"

    private const val mockkVersion = "1.10.0"
    const val mockk = "io.mockk:mockk:$mockkVersion"
    const val mockkAndroid = "io.mockk:mockk-android:$mockkVersion"

    private const val turbineVersion = "0.7.0"
    const val turbine = "app.cash.turbine:turbine:$turbineVersion"

    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Compose.composeVersion}"

    const val hiltTesting = "com.google.dagger:hilt-android-testing:${DaggerHilt.version}"

    private const val testRunnerVersion = "1.4.0"
    const val testRunner = "androidx.test:runner:$testRunnerVersion"
    const val composeBomb = "androidx.compose:compose-bom:2022.10.00"
    const val composeJunit4 = "androidx.compose.ui:ui-test-junit4"
    const val composeTooling = "androidx.compose.ui:ui-tooling"
    const val  composeManifest = "androidx.compose.ui:ui-test-manifest"

}