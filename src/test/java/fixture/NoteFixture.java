package fixture;

import com.godoineto.simplepipe.model.Note;

public class NoteFixture {

    public static Note get() {
        Note note = new Note();
        note.setText("Just a simple note");
        return note;
    }
}
