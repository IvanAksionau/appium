package ivan.demo.com.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public final class BrowserStackCloudUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserStackCloudUtil.class);
    private static final Properties PROPERTIES = PropsUtil.getProps();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private BrowserStackCloudUtil() {
    }

    private static String buildCommand(String filePath) {
        return "curl -u \""
                .concat(PROPERTIES.getProperty("browserstack.user"))
                .concat(":")
                .concat(PROPERTIES.getProperty("browserstack.key"))
                .concat("\" -X POST \"")
                .concat(PROPERTIES.getProperty("browserstack.upload.url"))
                .concat("\" -F \"file=@")
                .concat(System.getProperty("user.dir"))
                .concat("\\")
                .concat(filePath.replace("/", "\\"))
                .concat("\"");
    }

    public static String uploadApkFile(String filePath) {
        Process process;
        String command = buildCommand(filePath);
        try {
            LOGGER.info("APK is uploading to a Browserstack resource...");
            process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            throw new RuntimeException("BrowserStack file upload is failed", e);
        }
        String appId = extractAppUrl(process);
        LOGGER.info("APK is uploaded to a Browserstack with ID: " + appId);
        return appId;
    }

    private static String extractAppUrl(Process process) {
        String output;
        JsonNode jsonNode;

        output = readProcess(process, "success");
        try {
            jsonNode = MAPPER.readTree(output);
        } catch (JsonProcessingException e) {
            output = readProcess(process, "error");
            throw new RuntimeException("Cannot properly map cmd process output as output is: \n".concat(output), e);
        }
        return jsonNode.get("app_url").asText();
    }

    private static String readProcess(Process process, String processType) {
        String output;
        BufferedReader reader;
        if (processType.equals("success")) {
            reader = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));
        } else {
            reader = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));
        }
        try {
            output = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read cmd process output", e);
        }
        return output;
    }
}
