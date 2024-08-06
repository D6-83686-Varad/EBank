package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.BankDao;
import com.app.dto.BankDTO;
import com.app.entity.bank.Bank;
import com.app.exceptions.ResourceNotFoundException;

@Service
@Transactional
public class BankServiceImpl implements BankService {

    @Autowired
    private BankDao bankDao;

    @Autowired
    private ModelMapper mapper;

    @Override
    public String addBank(BankDTO bank) {
        Bank bankEntity = mapper.map(bank, Bank.class);
        bankDao.save(bankEntity);
        return "Bank added successfully";
    }

    @Override
    public Bank getBankDetails() {
        return bankDao.getBankDetails().orElseThrow(() -> new ResourceNotFoundException("Bank not found"));
    }

    // Method to add to fundAvailable
    @Override
    public boolean addFundAvailable(double fundAvailable) {
        Bank bank = getBankDetails();
        bank.setFundAvailable(bank.getFundAvailable() + fundAvailable);
        bankDao.save(bank);
        return true;
    }

    // Method to subtract from fundAvailable
    @Override
    public boolean subtractFundAvailable(double fundAvailable) {
        Bank bank = getBankDetails();
        double loanExpected = bank.getLoanExpected();
        double loanRecovered = bank.getLoanRecovered();
        double fundAvailableCurrent = bank.getFundAvailable();

        double newFundAvailable = fundAvailableCurrent - fundAvailable;
        double combinedAmount = loanExpected - loanRecovered + newFundAvailable;

        if (combinedAmount <= 0 || newFundAvailable < 0 || combinedAmount > 0.05 * (loanExpected - loanRecovered + fundAvailableCurrent)) {
            return false; // Invalid operation
        }

        bank.setFundAvailable(newFundAvailable);
        bankDao.save(bank);
        return true;
    }

    // Method to add to fundReceived
    @Override
    public boolean addFundReceived(double fundReceived) {
        Bank bank = getBankDetails();
        bank.setFundReceived(bank.getFundReceived() + fundReceived);
        bankDao.save(bank);
        return true;
    }

    // Method to subtract from fundReceived
    @Override
    public boolean subtractFundReceived(double fundReceived) {
        Bank bank = getBankDetails();
        if (bank.getFundReceived() - fundReceived < 0) {
            return false; // Invalid operation
        }
        bank.setFundReceived(bank.getFundReceived() - fundReceived);
        bankDao.save(bank);
        return true;
    }

    // Method to add to fundToPay
    @Override
    public boolean addFundToPay(double fundToPay) {
        Bank bank = getBankDetails();
        bank.setFundToPay(bank.getFundToPay() + fundToPay);
        bankDao.save(bank);
        return true;
    }

    // Method to subtract from fundToPay
    @Override
    public boolean subtractFundToPay(double fundToPay) {
        Bank bank = getBankDetails();
        if (bank.getFundToPay() - fundToPay < 0) {
            return false; // Invalid operation
        }
        bank.setFundToPay(bank.getFundToPay() - fundToPay);
        bankDao.save(bank);
        return true;
    }

    // Method to add to loanDisbursed
    @Override
    public boolean addLoanDisbursed(double loanDisbursed) {
        Bank bank = getBankDetails();
        bank.setLoanDisbursed(bank.getLoanDisbursed() + loanDisbursed);
        bankDao.save(bank);
        return true;
    }

    // Method to subtract from loanDisbursed
    @Override
    public boolean subtractLoanDisbursed(double loanDisbursed) {
        Bank bank = getBankDetails();
        if (bank.getLoanDisbursed() - loanDisbursed < 0) {
            return false; // Invalid operation
        }
        bank.setLoanDisbursed(bank.getLoanDisbursed() - loanDisbursed);
        bankDao.save(bank);
        return true;
    }

    // Method to add to loanRecovered
    @Override
    public boolean addLoanRecovered(double loanRecovered) {
        Bank bank = getBankDetails();
        bank.setLoanRecovered(bank.getLoanRecovered() + loanRecovered);
        bankDao.save(bank);
        return true;
    }

    // Method to subtract from loanRecovered
    @Override
    public boolean subtractLoanRecovered(double loanRecovered) {
        Bank bank = getBankDetails();
        if (bank.getLoanRecovered() - loanRecovered < 0) {
            return false; // Invalid operation
        }
        bank.setLoanRecovered(bank.getLoanRecovered() - loanRecovered);
        bankDao.save(bank);
        return true;
    }

    // Method to add to loanExpected
    @Override
    public boolean addLoanExpected(double loanExpected) {
        Bank bank = getBankDetails();
        bank.setLoanExpected(bank.getLoanExpected() + loanExpected);
        bankDao.save(bank);
        return true;
    }

    // Method to subtract from loanExpected
    @Override
    public boolean subtractLoanExpected(double loanExpected) {
        Bank bank = getBankDetails();
        if (bank.getLoanExpected() - loanExpected < 0) {
            return false; // Invalid operation
        }
        bank.setLoanExpected(bank.getLoanExpected() - loanExpected);
        bankDao.save(bank);
        return true;
    }
}
