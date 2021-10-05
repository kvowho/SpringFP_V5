package net.voznjuk.fp.controller;

import net.voznjuk.fp.domain.Invoice;
import net.voznjuk.fp.domain.Message;
import net.voznjuk.fp.domain.User;
import net.voznjuk.fp.repos.InvoiceRepo;
import net.voznjuk.fp.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    //private MessageRepo messageRepo;
    private InvoiceRepo invoiceRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        //Iterable<Message> messages = messageRepo.findAll();
        Iterable<Invoice> invoices = invoiceRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            invoices = invoiceRepo.findByStatus(filter);
        } else {
            invoices = invoiceRepo.findAll();
        }

        model.addAttribute("invoices", invoices);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String comment,
            @RequestParam String status, Map<String, Object> model
    ) {
        Invoice invoice = new Invoice(comment, status, user);

        invoiceRepo.save(invoice);

        Iterable<Invoice> invoices = invoiceRepo.findAll();

        model.put("invoices", invoices);
        model.put("filter", "");

        return "main";
    }
}
