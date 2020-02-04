package fixture;

import com.godoineto.simplepipe.api.dto.NoteDTO;

public class NoteDTOFixture {

    public static NoteDTO get() {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setText("Just a simple note");
        return noteDTO;
    }
}
