/* (C)2022 Makepad SARL*/
package ${packagePrefix}.web.frontend;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public abstract class TemplateManager {
    private static final PebbleEngine pebbleEngine = new PebbleEngine.Builder().build();
    private static String DEFAULT_TEMPLATES_LOCATION = "templates";
    private static String DEFAULT_TEMPLATE_EXTENSION = "peb";x

    /**
     * Get the template content with a context
     *
     * @param templateName The name of the template
     * @param context The context of the template execution
     * @return The HTML string created from the template with the context
     * @throws IOException If the template file does not exist
     */
    public static String getTemplate(String templateName, Map<String, Object> context)
            throws IOException {
        Writer writer = new StringWriter();
        PebbleTemplate template =
                TemplateManager.pebbleEngine.getTemplate(buildTemplatePath(templateName));
        if (context == null) {
            template.evaluate(writer);
        } else {
            template.evaluate(writer, context);
        }
        return writer.toString();
    }

    /**
     * Get the template content without a context
     *
     * @param templateName The name of the template to evaluate
     * @return The HTML string related to the given template
     * @throws IOException If the given template does not exists
     */
    public static String getTemplate(String templateName) throws IOException {
        return TemplateManager.getTemplate(templateName, null);
    }

    private static String buildTemplatePath(String fileName) {
        return "%s/%s.%s"
                .formatted(DEFAULT_TEMPLATES_LOCATION, fileName, DEFAULT_TEMPLATE_EXTENSION);
    }
}
