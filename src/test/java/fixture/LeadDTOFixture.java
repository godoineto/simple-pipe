package fixture;

import com.godoineto.simplepipe.api.dto.LeadDTO;
import com.godoineto.simplepipe.model.LeadStatus;

import java.util.Arrays;

public class LeadDTOFixture {

    public static LeadDTO get() {
        LeadDTO leadDTO = new LeadDTO();
        leadDTO.setId("5e37150298c04776383f83a5");
        leadDTO.setName("Lead Name");
        leadDTO.setCompany("Lead Company");
        leadDTO.setEmail("lead@email.com");
        leadDTO.setStatus(LeadStatus.OPEN);
        leadDTO.setSite("https://www.lead.com.br");
        leadDTO.setPhones(Arrays.asList("48988880000", "48911110022"));
        return leadDTO;
    }
}
