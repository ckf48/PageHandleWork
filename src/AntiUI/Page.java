package AntiUI;

public class Page {
    private int pageNumber;
    private byte[] pageContent;

    public Page(int pageNumber, byte[] pageContent) {
        this.pageNumber = pageNumber;
        this.pageContent = pageContent;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public byte[] getPageContent() {
        return pageContent;
    }
}
