package AntiUI;

public class PageTable {
    private FileOperator operator = FileOperator.getInstance();
    private LRUstack pageNumbers = new LRUstack(16);
    private byte[] textContent;

    public void readText(int pageNumber) {
        pageNumbers.pushBack(pageNumber);
        Page page = (Page) operator.getPages().get(pageNumber);
        textContent = page.getPageContent();
    }

    public byte[] getTextContent() {
        return textContent;
    }

    public int getSize(){
        return pageNumbers.getSize();
    }

    public int get(int index){
        return pageNumbers.get(index);
    }


}
