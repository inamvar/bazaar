package com.kalatag.service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.kalatag.domain.Journal;
import com.kalatag.service.accounting.AccountService;
import com.kalatag.service.accounting.JournalService;

@Component("journalListener")
public class JournalListener {

	private Logger log = LoggerFactory.getLogger(JournalListener.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private JournalService journalService;

	@Autowired
	private AccountService accountService;

	public void onMessage(Journal journal) {

		try {

			journal = journalService.create(journal);
			if (journal != null) {
				log.debug("Journal: " + journal.getAccount().getName()
						+ ", amount:" + journal.getAmount() + ", Type:"
						+ journal.getType() + "  inserted successfully.");
				switch (journal.getType()) {
				case CREDIT:
					accountService.credit(journal.getAccount().getId(),
							journal.getAmount());
					break;
				case DEBIT:
					accountService.debit(journal.getAccount().getId(),
							journal.getAmount());
					break;
				default:
					break;

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
