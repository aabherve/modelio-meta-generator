package org.modeliosoft.modelio.metagenerator.i18n;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("7dd963da-6e69-4944-8286-14037ab70523")
public class Messages {
    @objid ("bf283eca-3c6c-4c47-9c02-5c8de360678b")
    private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle ("org.modeliosoft.modelio.metagenerator.i18n.messages");

    @objid ("7000ba4d-1cfd-4a16-b481-0913e1eeff60")
    private Messages() {
        
    }

    @objid ("357985ee-f314-4711-af20-9f7cec012568")
    public static String getString(final String key) {
        try {
            return RESOURCE_BUNDLE.getString (key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    @objid ("6ba17701-1e94-40a4-a5c4-8fd994f8d7f2")
    public static String getString(final String key, final String... params) {
        try {
            return MessageFormat.format (RESOURCE_BUNDLE.getString (key),(Object[]) params);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

}
