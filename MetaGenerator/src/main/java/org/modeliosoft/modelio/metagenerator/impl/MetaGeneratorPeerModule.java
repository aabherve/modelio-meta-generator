package org.modeliosoft.modelio.metagenerator.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IModuleAPIConfiguration;
import org.modelio.vbasic.version.Version;
import org.modeliosoft.modelio.metagenerator.api.IMetaGeneratorPeerModule;

@objid ("0b47e9e3-9f25-4488-97f6-04975a7e1207")
public class MetaGeneratorPeerModule implements IMetaGeneratorPeerModule {
    @objid ("f05bdb44-9717-4bd7-83a2-9f0bbf5dfed9")
    private MetaGeneratorModule module = null;

    @objid ("6e1ff372-5061-400c-9c0d-ccefd61490a7")
    private IModuleAPIConfiguration peerConfiguration;

    @objid ("f7e9f32f-7a96-4b39-9544-f31e1d968d8d")
    public MetaGeneratorPeerModule(final MetaGeneratorModule module, final IModuleAPIConfiguration peerConfiguration) {
        this.module = module;
        this.peerConfiguration = peerConfiguration;
    }

    @objid ("e9e4b619-7bc0-4b17-b284-f2ddacbed9a5")
    public IModuleAPIConfiguration getConfiguration() {
        
        return this.peerConfiguration;
    }

    @objid ("0e000cc2-403b-4007-a09d-06293e052f1e")
    public String getDescription() {
        
        return this.module.getDescription();
    }

    @objid ("605a0ad6-bbe4-41bb-90eb-d216aaa749cc")
    public String getName() {
        
        return this.module.getName();
    }

    @objid ("ac32fa62-8628-4b1c-825f-1bddf32d76b7")
    public Version getVersion() {
        
        return this.module.getVersion();
    }

    @objid ("cdc2c79b-b2e7-4808-8ddf-8288c7988355")
    void init() {
        
    }

}
