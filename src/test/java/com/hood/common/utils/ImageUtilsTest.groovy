package com.hood.common.utils

/**
 * @author taoranxue on 9/11/16 6:48 PM.
 */
class ImageUtilsTest extends GroovyTestCase {
    void testConvertBase64Content() {
        ImageUtils.convertBase64Content("<p><img src=\"data:image/png;base64,iVBORw0KGg\" style=\"width: 700px;\"><br></p>");
    }
}
