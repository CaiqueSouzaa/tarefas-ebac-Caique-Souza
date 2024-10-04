package br.com.csouza.services;

import br.com.csouza.entity.Contract;
import br.com.csouza.interfaces.IContractDAO;

public class ContractService extends GenericService<Contract> {
    public ContractService(IContractDAO contractDAO) {
        super(contractDAO);
    }
}
