package ivan.demo.com;

public interface FilePathProvider {

    default String getResourceFilePath() {
        return "";
    }
}
