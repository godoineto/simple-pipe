package fixture;

import com.godoineto.simplepipe.domain.Note;

public class NoteFixture {

    public static Note get() {
        Note note = new Note();
        note.setText("Just a simple note");
        return note;
    }
}
