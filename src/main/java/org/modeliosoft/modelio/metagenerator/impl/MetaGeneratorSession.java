package org.modeliosoft.modelio.metagenerator.impl;

import java.util.Map;

import org.modelio.api.module.DefaultModuleSession;
import org.modelio.api.module.ModuleException;
import org.modelio.vbasic.version.Version;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("7940a3d9-1f80-4128-a259-12c84707dc28")
public class MetaGeneratorSession extends DefaultModuleSession {
    @objid ("84cad32d-6a21-46ad-987d-0866e9a74fef")
    public MetaGeneratorSession(final MetaGeneratorModule module) {
        super(module);
    }

    @objid ("7da40d9a-3c54-43a1-9028-84f37224bdf4")
    @Override
    public boolean start() throws ModuleException {
        
        return super.start();
    }

    @objid ("fdd51027-e08f-4ad3-8b8a-e30bfae7d2a5")
    @Override
    public void stop() throws ModuleException {
        super.stop();
    }

    /**
     * @param mdaPath @return
     */
    @objid ("f9e78777-88ff-49b9-acae-e20930de3967")
    public static boolean install(final String modelioPath, final String mdaPath) throws ModuleException {
        
        return DefaultModuleSession.install(modelioPath, mdaPath);
    }

    @objid ("90fa89ec-3ddc-4257-9caa-9ed6ea2a912c")
    @Override
    public boolean select() throws ModuleException {
        
        return super.select();
    }

    @objid ("c4b82610-f4a1-4c4f-b1e9-e14e7d4b7213")
    @Override
    public void upgrade(final Version oldVersion, final Map<String, String> oldParameters) throws ModuleException {
        super.upgrade(oldVersion, oldParameters);
    }

    @objid ("30deba26-24de-45c1-923d-a9db86b7d332")
    @Override
    public void unselect() throws ModuleException {
        super.unselect();
    }

}
