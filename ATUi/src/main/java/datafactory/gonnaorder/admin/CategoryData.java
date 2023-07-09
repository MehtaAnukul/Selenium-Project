package datafactory.gonnaorder.admin;

import dataobjects.gonnaorder.admin.Category;
import utilities.JavaHelpers;

public class CategoryData {
    /**
     * Generate test data for category information
     *
     * @return Category object
     */
    public Category getCategoryData() {
        JavaHelpers java = new JavaHelpers();
        Category info = new Category();
        String randomString = "Category" + java.getTimeStamp("yyyyMMdd_HHmmss");
        info.setName(randomString);
        info.setShortDesc("Category Created via Automation");
        info.setSellable(true);
        return info;

    }

    /**
     * Generate edit test data for category information
     *
     * @return Category object
     */
    public Category getCategoryEditData() {
        JavaHelpers java = new JavaHelpers();
        Category editInfo = new Category();
        String randomString = "CategoryEdit" + java.getTimeStamp("yyyyMMdd_HHmmss");
        editInfo.setName(randomString);
        editInfo.setShortDesc("Category Edited via Automation");
        editInfo.setSellable(true);
        return editInfo;

    }

}