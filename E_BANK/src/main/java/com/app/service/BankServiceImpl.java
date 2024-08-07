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

    /**
     * Adds a new bank.
     *
     * @param bank the BankDTO containing bank details.
     * @return a success message.
     */
    @Override
    public String addBank(BankDTO bank) {
        Bank bankEntity = mapper.map(bank, Bank.class);
        bankDao.save(bankEntity);
        return "Bank added successfully";
    }

    /**
     * Retrieves the bank details.
     *
     * @return the Bank entity.
     * @throws ResourceNotFoundException if the bank is not found.
     */
    @Override
    public Bank getBankDetails() {
        return bankDao.getBankDetails().orElseThrow(() -> new ResourceNotFoundException("Bank not found"));
    }

    /**
     * Adds funds to the available funds.
     *
     * @param amount the amount to be added.
     * @return true if the operation is successful.
     */
    @Override
    public boolean addFundAvailable(double amount) {
        Bank bank = getBankDetails();
        bank.setFundAvailable(bank.getFundAvailable() + amount);
        bankDao.save(bank);
        return true;
    }

    /**
     * Subtracts funds from the available funds.
     *
     * @param amount the amount to be subtracted.
     * @return true if the operation is successful, false otherwise.
     */
    @Override
    public boolean subtractFundAvailable(double amount) {
        Bank bank = getBankDetails();
        double loanExpected = bank.getLoanExpected();
        double loanRecovered = bank.getLoanRecovered();
        double currentFundAvailable = bank.getFundAvailable();

        double newFundAvailable = currentFundAvailable - amount;
        double combinedAmount = loanExpected - loanRecovered + newFundAvailable;

        // Validate the operation
        if (newFundAvailable < 0 || combinedAmount <= 0 || combinedAmount > 0.3 * (loanExpected - loanRecovered + currentFundAvailable)) {
            return false;
        }

        bank.setFundAvailable(newFundAvailable);
        bankDao.save(bank);
        return true;
    }

    /**
     * Adds funds to the received funds.
     *
     * @param amount the amount to be added.
     * @return true if the operation is successful.
     */
    @Override
    public boolean addFundReceived(double amount) {
        Bank bank = getBankDetails();
        bank.setFundReceived(bank.getFundReceived() + amount);
        bankDao.save(bank);
        return true;
    }

    /**
     * Subtracts funds from the received funds.
     *
     * @param amount the amount to be subtracted.
     * @return true if the operation is successful, false otherwise.
     */
    @Override
    public boolean subtractFundReceived(double amount) {
        Bank bank = getBankDetails();
        if (bank.getFundReceived() - amount < 0) {
            return false;
        }
        bank.setFundReceived(bank.getFundReceived() - amount);
        bankDao.save(bank);
        return true;
    }

    /**
     * Adds funds to the funds to pay.
     *
     * @param amount the amount to be added.
     * @return true if the operation is successful.
     */
    @Override
    public boolean addFundToPay(double amount) {
        Bank bank = getBankDetails();
        bank.setFundToPay(bank.getFundToPay() + amount);
        bankDao.save(bank);
        return true;
    }

    /**
     * Subtracts funds from the funds to pay.
     *
     * @param amount the amount to be subtracted.
     * @return true if the operation is successful, false otherwise.
     */
    @Override
    public boolean subtractFundToPay(double amount) {
        Bank bank = getBankDetails();
        if (bank.getFundToPay() - amount < 0) {
            return false;
        }
        bank.setFundToPay(bank.getFundToPay() - amount);
        bankDao.save(bank);
        return true;
    }

    /**
     * Adds funds to the loan disbursed.
     *
     * @param amount the amount to be added.
     * @return true if the operation is successful.
     */
    @Override
    public boolean addLoanDisbursed(double amount) {
        Bank bank = getBankDetails();
        bank.setLoanDisbursed(bank.getLoanDisbursed() + amount);
        bankDao.save(bank);
        return true;
    }

    /**
     * Subtracts funds from the loan disbursed.
     *
     * @param amount the amount to be subtracted.
     * @return true if the operation is successful, false otherwise.
     */
    @Override
    public boolean subtractLoanDisbursed(double amount) {
        Bank bank = getBankDetails();
        if (bank.getLoanDisbursed() - amount < 0) {
            return false;
        }
        bank.setLoanDisbursed(bank.getLoanDisbursed() - amount);
        bankDao.save(bank);
        return true;
    }

    /**
     * Adds funds to the loan recovered.
     *
     * @param amount the amount to be added.
     * @return true if the operation is successful.
     */
    @Override
    public boolean addLoanRecovered(double amount) {
        Bank bank = getBankDetails();
        bank.setLoanRecovered(bank.getLoanRecovered() + amount);
        bankDao.save(bank);
        return true;
    }

    /**
     * Subtracts funds from the loan recovered.
     *
     * @param amount the amount to be subtracted.
     * @return true if the operation is successful, false otherwise.
     */
    @Override
    public boolean subtractLoanRecovered(double amount) {
        Bank bank = getBankDetails();
        if (bank.getLoanRecovered() - amount < 0) {
            return false;
        }
        bank.setLoanRecovered(bank.getLoanRecovered() - amount);
        bankDao.save(bank);
        return true;
    }

    /**
     * Adds funds to the loan expected.
     *
     * @param amount the amount to be added.
     * @return true if the operation is successful.
     */
    @Override
    public boolean addLoanExpected(double amount) {
        Bank bank = getBankDetails();
        bank.setLoanExpected(bank.getLoanExpected() + amount);
        bankDao.save(bank);
        return true;
    }

    /**
     * Subtracts funds from the loan expected.
     *
     * @param amount the amount to be subtracted.
     * @return true if the operation is successful, false otherwise.
     */
    @Override
    public boolean subtractLoanExpected(double amount) {
        Bank bank = getBankDetails();
        if (bank.getLoanExpected() - amount < 0) {
            return false;
        }
        bank.setLoanExpected(bank.getLoanExpected() - amount);
        bankDao.save(bank);
        return true;
    }
}
