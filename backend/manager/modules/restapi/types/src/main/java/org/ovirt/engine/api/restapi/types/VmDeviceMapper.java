package org.ovirt.engine.api.restapi.types;

import org.ovirt.engine.api.model.Vm;
import org.ovirt.engine.api.model.VmDevice;
import org.ovirt.engine.core.utils.SerializationFactory;

public class VmDeviceMapper {
    @Mapping(from = org.ovirt.engine.core.common.businessentities.VmDevice.class, to = VmDevice.class)
    public static VmDevice map(org.ovirt.engine.core.common.businessentities.VmDevice a, VmDevice b) {
        VmDevice result = b == null ? new VmDevice() : b;
        if (a.getId() != null) {
            result.setId(a.getId().getDeviceId().toString());
        }
        if (a.getVmId() != null) {
            result.setVm(new Vm());
            result.getVm().setId(a.getVmId().toString());
        }
        if (a.getType() != null) {
            result.setType(a.getType().getValue());
        }
        if (a.getDevice() != null) {
            result.setDevice(a.getDevice());
        }
        if (a.getAddress() != null) {
            result.setAddress(a.getAddress());
        }
        result.setReadOnly(a.getReadOnly());
        result.setPlugged(a.isPlugged());
        result.setManaged(a.isManaged());
        if (a.getAlias() != null) {
            result.setAlias(a.getAlias());
        }
        if (a.getSpecParams() != null) {
            result.setSpecParams(SerializationFactory.getSerializer().serialize(a.getSpecParams()));
        }
        return result;
    }
}
