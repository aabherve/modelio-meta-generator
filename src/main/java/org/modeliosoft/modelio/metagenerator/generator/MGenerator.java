package org.modeliosoft.modelio.metagenerator.generator;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.AggregationKind;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Package;

@objid ("e7b121a7-efc8-42bb-9cd7-0aa42b8b2cfc")
public class MGenerator {
    @objid ("bff58f90-c36c-4174-9886-8d0c1ba8df66")
    private Package metamodelRoot;

    @objid ("8f4bf781-e3f7-4a04-b698-2f5db4fa03ef")
    public MGenerator(Package metamodelRoot) {
        this.metamodelRoot = metamodelRoot;
    }

    @objid ("3809faf8-fd1c-4304-9ec7-2ee05972ab08")
    public StringBuffer generateElements() {
        StringBuffer result = new StringBuffer();

        result.append("// ########### Package / Class / Attributs ###########\n\n");

        for (ModelTree sub : metamodelRoot.getOwnedElement()) {
            if (sub instanceof Package) {
                result.append(createPackage((Package) sub));
            }
        }
        return result;
    }

    @objid ("35eb65d3-8e87-4971-9862-aaf7877d42f4")
    public StringBuffer generateDependencys() {
        StringBuffer result = new StringBuffer();
        result.append("\n\n\n // ########### Dependencies ###########\n\n");

        for (AssociationEnd end : getDependency()) {
            result.append(createDependency(end));
        }
        return result;
    }

    @objid ("aadcbc69-6d29-4f7f-8367-e0f121fde46a")
    public StringBuffer generateGeneralizations() {
        StringBuffer result = new StringBuffer();
        result.append("\n\n\n // ########### Generalizations ###########\n\n");
        for (Class mClass : getMClass()) {
            if (mClass.getParent().size() + mClass.getSpecialization().size() > 0) {
                result.append(createGeneralization(mClass));
            }
        }
        return result;
    }

    @objid ("a7c8cadf-13c3-4b4c-b938-50f50c442396")
    public StringBuffer createPackage(Package mPackage) {
        StringBuffer result = new StringBuffer();

        result.append("//##### Package " + mPackage.getName() + "#####\n");

        result.append("MPackage " + IdGenerator.createId(mPackage)
                + " = new MPackage(\"" + mPackage.getUuid().toString()
                + "\",\"" + mPackage.getName() + "\",\""
                + mPackage.getUuid().toString() + ".exml\");\n");

        if (mPackage.getOwner().equals(this.metamodelRoot)) {
            result.append("this.mPackages.add("
                    + IdGenerator.createId(mPackage) + ");\n");
        } else {
            result.append(IdGenerator.createId(mPackage.getOwner())
                    + ".getMPackages().add(" + IdGenerator.createId(mPackage)
                    + ");\n");
        }

        for (ModelTree sub : mPackage.getOwnedElement()) {
            if (sub instanceof Package) {
                result.append(createPackage((Package) sub));
            }
        }

        for (ModelTree sub : mPackage.getOwnedElement()) {
            if (sub instanceof Class) {
                result.append(createClass((Class) sub));
            }
        }

        result.append("\n");
        result.append("\n");
        return result;
    }

    @objid ("43f3d6bc-8505-42b4-a8dc-a0b3e0c5d31f")
    public StringBuffer createClass(Class mClass) {
        StringBuffer result = new StringBuffer();

        result.append("//## class " + mClass.getName() + " ##\n\n");

        result.append("MClass " + IdGenerator.createId(mClass)
                + " = new MClass(\"" + mClass.getUuid().toString() + "\",\""
                + mClass.getName() + "\",\"" + mClass.getUuid().toString()
                + ".exml\");\n");

        result.append(IdGenerator.createId(mClass.getOwner())
                + ".getMClass().add(" + IdGenerator.createId(mClass) + ");\n");
        result.append("this.mClass.put(\"" + mClass.getName() + "\","
                + IdGenerator.createId(mClass) + ");\n");

        for (Attribute attr : mClass.getOwnedAttribute()) {
            result.append(createAttribute(attr));
        }

        result.append("\n");
        return result;
    }

    @objid ("51bf6403-ae7a-4d66-8fab-a21479c3fa5a")
    public StringBuffer createAttribute(Attribute mAttr) {
        StringBuffer result = new StringBuffer();

        result.append("// attribute " + mAttr.getName() + "\n\n");
        result.append("MAttribute " + IdGenerator.createId(mAttr)
                + " = new MAttribute(\"" + mAttr.getUuid().toString() + "\",\""
                + mAttr.getName() + "\",\""
                + mAttr.getOwner().getUuid().toString()
                + ".exml\",this.mBaseTypes.get(\"" + mAttr.getType().getName()
                + "\"),false,true,false);\n");
        result.append(IdGenerator.createId(mAttr.getOwner())
                + ".getMAttributes().add(" + IdGenerator.createId(mAttr)
                + ");\n");

        result.append("\n");
        result.append("\n");
        return result;
    }

    @objid ("b22aac11-8e7e-4143-9087-95d3e7961e4b")
    private StringBuffer createGeneralization(Class mClass) {
        StringBuffer result = new StringBuffer();

        result.append("\n// Generalization : class " + mClass.getName() + "\n");

        for (Generalization gen : mClass.getParent()) {
            result.append("this.mClass.get(\"" + mClass.getName()
                    + "\").getMSuperType().add(this.mClass.get(\""
                    + gen.getSuperType().getName() + "\"));\n");
        }

        for (Generalization gen : mClass.getSpecialization()) {
            result.append("this.mClass.get(\"" + mClass.getName()
                    + "\").getMSubTypes().add(this.mClass.get(\""
                    + gen.getSubType().getName() + "\"));\n");
        }
        return result;
    }

    @objid ("0592c086-ddcb-4778-bbf3-05c37e67affa")
    private StringBuffer createDependency(AssociationEnd mEnd) {
        StringBuffer result = new StringBuffer();
        result.append("\n// dependency " + mEnd.getOwner().getName() + "."
                + mEnd.getName() + "\n");

        String idtarget = null;
        if (mEnd.getOpposite() != null) {
            idtarget = mEnd.getOpposite().getOwner().getName();
        } else if (mEnd.getOppositeOwner() != null) {
            idtarget = mEnd.getOppositeOwner().getOwner().getName();
        }

        if (idtarget != null) {
            result.append("MDependency "
                    + IdGenerator.createId(mEnd)
                    + " = new MDependency(\""
                    + mEnd.getUuid().toString()
                    + "\",\""
                    + mEnd.getName()
                    + "\",\""
                    + mEnd.getOwner().getUuid().toString()
                    + ".exml\",this.mClass.get(\""
                    + idtarget
                    + "\"),"
                    + !mEnd.getMultiplicityMax().equals("1")
                    + ",true,true,"
                    + mEnd.getAggregation().equals(
                            AggregationKind.KINDISCOMPOSITION) + ");\n");
            result.append("this.mClass.get(\"" + mEnd.getOwner().getName()
                    + "\").getMDependencys().add(" + IdGenerator.createId(mEnd)
                    + ");\n");
        }
        return result;
    }

    @objid ("3502fbd9-47b5-4083-b64a-e2c9248d458f")
    private List<Class> getMClass() {
        List<Class> result = new ArrayList<>();
        for (Package pack : getMPackage(metamodelRoot)) {
            for (ModelTree subClass : pack.getOwnedElement()) {
                if (subClass instanceof Class) {
                    result.add((Class) subClass);
                }
            }
        }
        return result;
    }

    @objid ("472472b9-2e84-405c-9fb7-ef662c40ea94")
    private List<Package> getMPackage(ModelTree root) {
        List<Package> result = new ArrayList<>();
        for (ModelTree subPackage : root.getOwnedElement()) {
            if (subPackage instanceof Package) {
                result.add((Package) subPackage);
                result.addAll(getMPackage((Package) subPackage));
            }
        }
        return result;
    }

    @objid ("44630fb9-6a22-48e1-917c-e36a356db86e")
    private List<AssociationEnd> getDependency() {
        List<AssociationEnd> result = new ArrayList<>();
        for (Class subClass : getMClass()) {
            result.addAll(((Class) subClass).getOwnedEnd());
        }
        return result;
    }

    @objid ("959bdf4a-1391-49c1-b70b-9e7e7d08970d")
    public StringBuffer generateEnums() {
        StringBuffer result = new StringBuffer();
        result.append("\n\n\n // ########### Enums ###########\n\n");

        for (Enumeration enums : getEnumerations()) {
            result.append(createEnumerations(enums));
        }
        return result;
    }

    @objid ("ba23970e-6ec9-4ecf-a836-188bdddd15aa")
    private StringBuffer createEnumerations(Enumeration enums) {
        StringBuffer result = new StringBuffer();
        result.append("this.mBaseTypes.put(\"");
        result.append(enums.getName());
        result.append("\", new MEnum(\"");
        result.append(enums.getUuid().toString());
        result.append("\",\"");
        result.append(enums.getName());
        result.append("\",\"enum\",java.util.Arrays.asList(");
        for(EnumerationLiteral literal : enums.getValue()){
            result.append("\"" + literal.getName() +"\"");
            if(enums.getValue().indexOf(literal)  != enums.getValue().size() -1){
                result.append(",");
            }
        }
        result.append(")));\n");
        return result;
    }

    @objid ("f69a2521-f420-4c32-a101-72436f7ceae2")
    private List<Enumeration> getEnumerations() {
        List<Enumeration> result = new ArrayList<>();
        for (Package pack : getMPackage(metamodelRoot)) {
            for (ModelTree subClass : pack.getOwnedElement()) {
                if (subClass instanceof Enumeration) {
                    result.add((Enumeration) subClass);
                }
            }
        }
        return result;
    }

}
