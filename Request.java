public class Request {
    private String englishText;
    private String targetLanguage;
    public Request(String englishText, String targetLanguage) {
        this.englishText = englishText;
        this.targetLanguage = targetLanguage;
    }
    public String getEnglishText() {
        return englishText;
    }
    public void setEnglishText(String englishText) {
        this.englishText = englishText;
    }
    public String getTargetLanguage() {
        return targetLanguage;
    }
    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }
}
