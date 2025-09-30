package org.ovirt.engine.core.bll;

import javax.inject.Inject;

import org.ovirt.engine.core.bll.context.EngineContext;
import org.ovirt.engine.core.common.queries.VmDeviceIdQueryParameters;
import org.ovirt.engine.core.dao.VmDeviceDao;

public class GetVmDeviceByIdQuery<P extends VmDeviceIdQueryParameters> extends QueriesCommandBase<P> {
    @Inject
    private VmDeviceDao vmDeviceDao;

    public GetVmDeviceByIdQuery(P parameters, EngineContext engineContext) {
        super(parameters, engineContext);
    }

    @Override
    protected void executeQueryCommand() {
        setReturnValue(
                vmDeviceDao.getVmDevicesByDeviceId(getParameters().getId().getDeviceId(), getParameters().getId().getVmId()));
    }
}
