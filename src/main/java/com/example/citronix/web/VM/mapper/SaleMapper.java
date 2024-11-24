package com.example.citronix.web.VM.mapper;

import com.example.citronix.model.Sale;
import com.example.citronix.web.VM.harvest.HarvestRequestVm;
import com.example.citronix.web.VM.harvest.HarvestResponseVm;
import com.example.citronix.web.VM.sale.SaleRequestVm;
import com.example.citronix.web.VM.sale.SaleResponseVm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    Sale toEntity(SaleRequestVm saleRequestVm);
    SaleResponseVm toResponseVm(Sale sale);
}
