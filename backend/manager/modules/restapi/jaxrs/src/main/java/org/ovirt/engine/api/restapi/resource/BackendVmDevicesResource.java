/*
 * Copyright oVirt Authors
 * SPDX-License-Identifier: Apache-2.0
*/

package org.ovirt.engine.api.restapi.resource;

import java.util.List;

import org.ovirt.engine.api.model.Vm;
import org.ovirt.engine.api.model.VmDevice;
import org.ovirt.engine.api.model.VmDevices;
import org.ovirt.engine.api.resource.VmVmDeviceResource;
import org.ovirt.engine.api.resource.VmVmDevicesResource;
import org.ovirt.engine.core.common.queries.IdQueryParameters;
import org.ovirt.engine.core.common.queries.QueryType;
import org.ovirt.engine.core.compat.Guid;

public class BackendVmDevicesResource extends AbstractBackendCollectionResource<VmDevice, org.ovirt.engine.core.common.businessentities.VmDevice> implements VmVmDevicesResource {

    private Guid vmId;

    public BackendVmDevicesResource(Guid vmId) {
        super(VmDevice.class, org.ovirt.engine.core.common.businessentities.VmDevice.class);
        this.vmId = vmId;
    }

    @Override
    public VmDevices list() {
        return mapCollection(getBackendCollection(QueryType.GetVmDevicesForVm, new IdQueryParameters(vmId)));
    }

    private VmDevices mapCollection(List<org.ovirt.engine.core.common.businessentities.VmDevice> entities) {
        VmDevices collection = new VmDevices();
        for (org.ovirt.engine.core.common.businessentities.VmDevice entity : entities) {
            collection.getVmDevices().add(addLinks(populate(map(entity), entity)));
        }
        return collection;
    }

    @Override
    public VmDevice addParents(VmDevice entity) {
        Vm vm = new Vm();
        vm.setId(vmId.toString());
        entity.setVm(vm);
        return entity;
    }

    @Override
    public VmVmDeviceResource getDeviceResource(String id) {
        return inject(new BackendVmDeviceResource(id, vmId));
    }

}
