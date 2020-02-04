package fixture;

import com.godoineto.simplepipe.model.Lead;
import com.godoineto.simplepipe.model.LeadStatus;

import java.util.ArrayList;
import java.util.Arrays;

public class LeadFixture {

    public static Lead get() {
        Lead lead = new Lead();
        lead.setId("5e37150298c04776383f83a5");
        lead.setName("Lead Name");
        lead.setCompany("Lead Company");
        lead.setEmail("lead@email.com");
        lead.setStatus(LeadStatus.OPEN);
        lead.setSite("https://www.lead.com.br");
        lead.setPhones(Arrays.asList("48988880000", "48911110022"));
        return lead;
    }

    public static Lead withNote() {
        Lead lead = get();
        lead.setNotes(new ArrayList<>());
        lead.getNotes().add(NoteFixture.get());
        return lead;
    }

}
