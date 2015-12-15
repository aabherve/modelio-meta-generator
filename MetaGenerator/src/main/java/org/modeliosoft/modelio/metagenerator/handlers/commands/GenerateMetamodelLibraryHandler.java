package org.modeliosoft.modelio.metagenerator.handlers.commands;

import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.model.ITransaction;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.module.IModule;
import org.modelio.api.module.commands.ApplyPatternStandardHandler;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modeliosoft.modelio.metagenerator.generator.MGenerator;

@objid ("a777bb7e-f43c-4765-b0d6-fd877650e84c")
public class GenerateMetamodelLibraryHandler extends ApplyPatternStandardHandler {
    @objid ("6c5f3087-29cf-4563-8eb4-d536f4618313")
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (super.accept(selectedElements, module) == false) {
            return false;
        }
        return true;
    }

    @objid ("04dab774-a6be-49d7-baa1-d3130275ada3")
    @Override
    protected void postConfigure(final List<MObject> selectedElements, final IModule module) {
        super.postConfigure(selectedElements, module);
        ModelTree selection = (ModelTree) selectedElements.get(0);
        
        Package metamodelRoot = Modelio.getInstance().getModelingSession().findElementById(Package.class, "bff2354a-6266-4202-917e-45585556eabf");
        MGenerator generator = new MGenerator(metamodelRoot);
        
        
        try (ITransaction tr = Modelio.getInstance().getModelingSession().createTransaction("Generate metamodel library")) {
            Operation initClassOp = findOperation(selection, "initClass");
            if (initClassOp != null) {
                StringBuffer metaLoader = generator.generateElements();
                initClassOp.putNoteContent("JavaDesigner", "JavaCode", metaLoader.toString());
            }
        
            Operation initGeneralizationsOp = findOperation(selection, "initGeneralizations");
            if (initGeneralizationsOp != null) {
                StringBuffer metaLoader = generator.generateGeneralizations();
                initGeneralizationsOp.putNoteContent("JavaDesigner", "JavaCode", metaLoader.toString());
            }
        
            Operation initDependencysOp = findOperation(selection, "initDependencys");
            if (initDependencysOp != null) {
                StringBuffer metaLoader = generator.generateDependencys();
                initDependencysOp.putNoteContent("JavaDesigner", "JavaCode", metaLoader.toString());
            }
        
            Operation initEnumOp = findOperation(selection, "initEnum");
            if (initEnumOp != null) {
                StringBuffer metaLoader = generator.generateEnums();
                initEnumOp.putNoteContent("JavaDesigner", "JavaCode", metaLoader.toString());
            }
        
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @objid ("74cad17e-9773-4a20-a0db-3a62ad70d2cb")
    @Override
    protected Map<String, Object> configure(final List<MObject> selectedElements, final IModule module) {
        
        return super.configure(selectedElements, module);
    }

    @objid ("8141743c-1935-4e39-bac8-74c0a2f8f037")
    private Operation findInitClassOperation(ModelTree selection) {
        Class mMetamodel = findMMetamodelClass(selection);
        
        if (mMetamodel != null) {
            for (Operation sub : mMetamodel.getOwnedOperation()) {
                if (sub.getName().equals("initMetamodel")) {
                    return sub;
                }
            }
        }
        return null;
    }

    @objid ("eb528aff-0bdf-45f5-831c-7ccc8d216f96")
    private Operation findOperation(ModelTree selection, String name) {
        Class mMetamodel = findMMetamodelClass(selection);
        
        if (mMetamodel != null) {
            for (Operation sub : mMetamodel.getOwnedOperation()) {
                if (sub.getName().equals(name)) {
                    return sub;
                }
            }
        }
        return null;
    }

    @objid ("251867e5-ef3d-4cf8-b640-3d60b0876f21")
    private Class findMMetamodelClass(ModelTree selection) {
        Package implPackage = null;
        for (ModelTree sub : selection.getOwnedElement()) {
            if (sub instanceof Package && sub.getName().equals("org.modelio.metamodel")) {
                implPackage = (Package) sub;
            }
        }
        
        Class mMetamodel = null;
        if (implPackage != null) {
            for (ModelTree sub : implPackage.getOwnedElement()) {
                if (sub instanceof Class && sub.getName().equals("MMetamodel")) {
                    mMetamodel = (Class) sub;
                }
            }
        }
        return mMetamodel;
    }

}
