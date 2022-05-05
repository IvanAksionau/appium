package ivan.demo.com.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public final class RuntimeUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RuntimeUtil.class);
    private static final File FILE = new File(
            System.getProperty("user.dir") + "/src/main/resources/startEmulator.bat");
    private static final int PORT = 4723;
    private static final Properties PROPERTIES = PropsUtil.getProps();
    private static AppiumDriverLocalService service;

    private RuntimeUtil() {
    }

    public static void startAppiumServer() {
        service = new AppiumServiceBuilder()
                .withIPAddress(PROPERTIES.getProperty("ip.address"))
                .usingPort(PORT)
                .build();
        if (!service.isRunning()) {
            LOGGER.info("Appium Service is starting");
            service.start();
        }
    }

    public static void stopAppiumServer() {
        if (service != null) {
            service.stop();
        }
    }

    // TODO: 5/4/2022 Add virtual device creation https://developer.android.com/studio/command-line/avdmanager
    public static void startEmulator() {
        try {
            initStartEmulatorFile();
            Runtime.getRuntime().exec(FILE.getAbsolutePath());
            LOGGER.info("Please wait for 20 sec till device emulator is starting");
            Thread.sleep(20000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Android emulator was not started", e);
        }
    }

    public static void stopEmulator() {
        try {
            initStopEmulatorFile();
            Runtime.getRuntime().exec(FILE.getAbsolutePath());
            LOGGER.info("Please wait for 10 sec till device emulator is stopping");
            Thread.sleep(7000);
            FILE.deleteOnExit();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Android emulator is not correctly stopped", e);
        }
    }

    private static void initStartEmulatorFile() throws IOException {
        PrintWriter writer = new PrintWriter(FILE.getAbsolutePath());
        writer.print("");
        writer.close();

        List<String> lines = Arrays.asList("cd " + PROPERTIES.getProperty("emulator.path"),
                "emulator -avd " + PROPERTIES.getProperty("device.name"));
        Path file = Paths.get(FILE.getAbsolutePath());
        Files.write(file, lines, StandardCharsets.UTF_8);
    }

    private static void initStopEmulatorFile() throws IOException {
        PrintWriter writer = new PrintWriter(FILE.getAbsolutePath());
        writer.print("");
        writer.close();

        //System.getProperty("emulator.name"); should be used in case of execution from Jenkins
        List<String> lines = Arrays.asList("cd " + PROPERTIES.getProperty("emulator.path"),
                "adb -s " + PROPERTIES.getProperty("emulator.name") + " emu kill");
        Path file = Paths.get(FILE.getAbsolutePath());
        Files.write(file, lines, StandardCharsets.UTF_8);
    }
}
