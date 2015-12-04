package org.modeliosoft.modelio.metagenerator.impl;

import org.modelio.api.model.IModelingSession;
import org.modelio.api.module.AbstractJavaModule;
import org.modelio.api.module.IModuleAPIConfiguration;
import org.modelio.api.module.IModuleSession;
import org.modelio.api.module.IModuleUserConfiguration;
import org.modelio.api.module.IParameterEditionModel;
import org.modelio.metamodel.mda.ModuleComponent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("9a643515-f38c-481a-b9e0-367cab9ce4fd")
public class MetaGeneratorModule extends AbstractJavaModule {
    @objid ("c2511025-8943-4b36-bd42-22195f516828")
    private MetaGeneratorPeerModule peerModule = null;

    @objid ("b3a09a1b-b3a7-4c4d-bd85-c64f5539938f")
    private MetaGeneratorSession session = null;

    @objid ("2f66f8b8-ec7f-4412-88f9-9dcebda607a3")
    public MetaGeneratorModule(final IModelingSession modelingSession, final ModuleComponent moduleComponent, final IModuleUserConfiguration mdacConfiguration, final IModuleAPIConfiguration peerConfiguration) {
        super(modelingSession, moduleComponent, mdacConfiguration);
        this.session = new MetaGeneratorSession(this);
        this.peerModule = new MetaGeneratorPeerModule(this, peerConfiguration);
        init();
    }

    @objid ("cd1d8ff9-2866-4836-9234-57b5f130f9d1")
    public MetaGeneratorPeerModule getPeerModule() {
        
        return this.peerModule;
    }

    /**
     * Return the session attaced to the current module. This session is used to manage the module lifecycle by declaring the
     * desired implementation on start, select... methods.
     */
    @objid ("5c88f372-73d6-4843-a851-dfca9a2a98c6")
    public IModuleSession getSession() {
        
        return this.session;
    }

    /**
     * Method automatically called just after the creation of the module. The module is automatically instanciated at the beginning
     * of the MDA lifecycle and constructor implementation is not accessible to the module developer. The <code>init</code> method
     * allows the developer to execute the desired initialization.
     */
    @objid ("e632aedb-a608-4d34-a263-eeb2bc6199cf")
    @Override
    public IParameterEditionModel getParametersEditionModel() {
        
        return super.getParametersEditionModel();
    }

    @objid ("f8d8d9bc-e0a6-446f-baa0-3364c7c2776b")
    @Override
    public String getModuleImagePath() {
        
        return "/res/icon/module.png";
    }

}
