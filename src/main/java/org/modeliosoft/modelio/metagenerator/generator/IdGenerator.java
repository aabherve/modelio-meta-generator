package org.modeliosoft.modelio.metagenerator.generator;

import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
/**
 * IdGenerator
 * @author aabherve
 *
 */
@objid ("7ee2ef53-c0ef-4579-9045-46493be2cb8c")
public class IdGenerator {
    @objid ("597c7ea6-6699-45c0-8755-f539ae10804a")
    public static String createId(MObject element) {
        
        return "id_" + element.getUuid().toString().replaceAll("-", "");
    }

}
