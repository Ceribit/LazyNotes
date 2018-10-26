package com.ceribit.android.lazynotes;

import com.ceribit.android.lazynotes.database.Note;
import com.ceribit.android.lazynotes.database.Note.Importance;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NoteUnitTest {

    private final Importance TEST_IMPORTANCE_ENUM = Importance.NOT_IMPORTANT;
    private final String INSERT_TITLE = "Title";
    private final String INSERT_DESCRIPTION = "Description";
    private final String INSERT_DIFFICULTY= "not_important";

    @Test
    public void entry_CorrectInstantiationGivenDifficultyString(){
        Note testNote = new Note(INSERT_TITLE, INSERT_DESCRIPTION, INSERT_DIFFICULTY);

        assertThat("Test title given normal parameters",
                testNote.getTitle(), is(equalTo(INSERT_TITLE)));

        assertThat("Test summary given normal parameters",
                testNote.getDescription(),is(equalTo(INSERT_DESCRIPTION)));

        assertThat("Test title given normal parameters",
                testNote.getImportance().toString(), is(equalTo(TEST_IMPORTANCE_ENUM.toString())));
    }

    @Test
    public void entry_CorrectInstantiationGivenDifficultyEnum(){
        Note testNote = new Note(INSERT_TITLE, INSERT_DESCRIPTION, TEST_IMPORTANCE_ENUM);

        assertThat("Test title given normal parameters",
                testNote.getTitle(), is(equalTo(INSERT_TITLE)));

        assertThat("Test summary given normal parameters",
                testNote.getDescription(),is(equalTo(INSERT_DESCRIPTION)));

        assertThat("Test title given normal parameters",
                testNote.getImportance(), is(equalTo(TEST_IMPORTANCE_ENUM)));
    }
}
