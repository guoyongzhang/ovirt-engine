/*
 * Copyright oVirt Authors
 * SPDX-License-Identifier: Apache-2.0
*/

package org.ovirt.engine.api.restapi.resource;

import org.ovirt.engine.api.model.Vm;
import org.ovirt.engine.api.model.VmDevice;
import org.ovirt.engine.api.resource.VmVmDeviceResource;
import org.ovirt.engine.core.common.businessentities.VmDeviceId;
import org.ovirt.engine.core.common.queries.QueryType;
import org.ovirt.engine.core.common.queries.VmDeviceIdQueryParameters;
import org.ovirt.engine.core.compat.Guid;

public class BackendVmDeviceResource extends AbstractBackendSubResource<VmDevice, org.ovirt.engine.core.common.businessentities.VmDevice> implements VmVmDeviceResource {
    private Guid vmId;

    public BackendVmDeviceResource(String VmDeviceId, Guid vmId) {
        super(VmDeviceId, VmDevice.class, org.ovirt.engine.core.common.businessentities.VmDevice.class);
        this.vmId = vmId;
    }

    @Override
    public VmDevice get() {
        return addLinks(
                performGet(QueryType.GetVmDeviceById, new VmDeviceIdQueryParameters(new VmDeviceId(guid, vmId))));
    }

    @Override
    public VmDevice addParents(VmDevice entity) {
        Vm vm = new Vm();
        vm.setId(vmId.toString());
        entity.setVm(vm);
        return entity;
    }
}
