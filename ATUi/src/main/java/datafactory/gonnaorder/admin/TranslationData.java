package datafactory.gonnaorder.admin;

import dataobjects.gonnaorder.admin.Translation;
import utilities.JavaHelpers;

public class TranslationData {

    /**
 * Generate test data for translation information
 *
 * @return Store object
 */
        public Translation getTranslationDetailsData() {
            Translation info = new Translation();
            JavaHelpers java = new JavaHelpers();
            info.setLanguage("Greek");
            String randomString = "Translation" + java.getTimeStamp("yyyyMMdd_HHmmss");
            info.setName(randomString);
            info.setShortDescription("Add short description of Language Translation via Automation");
            return info;

    }

    /**
     * Generate edit test data for translation information
     *
     * @return Translation object
     */
    public Translation getTranslationEditData() {
        JavaHelpers java = new JavaHelpers();
        Translation editInfo = new Translation();
        String randomString = "TranslationEdit" + java.getTimeStamp("yyyyMMdd_HHmmss");
        editInfo.setName(randomString);
        editInfo.setShortDescription("Short description of translation Edited via Automation");
        return editInfo;

    }
}


