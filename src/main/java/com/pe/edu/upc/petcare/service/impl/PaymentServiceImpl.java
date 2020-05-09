package com.pe.edu.upc.petcare.service.impl;


import com.pe.edu.upc.petcare.model.Payment;
import com.pe.edu.upc.petcare.repository.PaymentRepository;
import com.pe.edu.upc.petcare.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    /*
        @Override
        public List<Customer> findCustomerAll() {
            return customerRepository.findAll();
        }

        @Override
        public Customer createCustomer(Customer customer) {
            /*Customer customerDB= customerRepository.findByNumberId((customer.getNumberId()));
            if (customerDB != null){
                return customerDB;
            }
            customer.setState("CREATED");
           // Customer customerDB = customerRepository.save(customer);
            return customerRepository.save(customer);
        }
    */
    @Override
    public Payment save(Payment payment) throws Exception {
        Payment paymentDB= paymentRepository.findById((payment.getId())).orElse(null);
        if (paymentDB != null){
            return paymentDB;
        }
        return paymentRepository.save(payment);
    }

    @Override
    public Payment update(Payment payment) throws Exception {
        Payment paymentDB= paymentRepository.findById(payment.getId()).orElse(null);
        if (paymentDB == null){
            return null;
        }
        paymentDB.setCard_number(payment.getCard_number());
        paymentDB.setCvv_number(payment.getCvv_number());
        paymentDB.setExpiry_date(payment.getExpiry_date());
        paymentDB.setName(payment.getName());

        return paymentRepository.save(paymentDB);
    }



    @Override
    public Payment findById(Long id) throws Exception {
        return paymentRepository.findById(id).orElse(null);
    }



    @Override
    public List<Payment> findAll() throws Exception {
        return paymentRepository.findAll();
    }

    @Override
    public Payment deleteById(Long id) throws Exception {
        Payment paymentDB= paymentRepository.findById(id).orElse(null);

        if (paymentDB != null){
            paymentRepository.deleteById(paymentDB.getId());
            return paymentDB;
        }

        return null;
    }
}
