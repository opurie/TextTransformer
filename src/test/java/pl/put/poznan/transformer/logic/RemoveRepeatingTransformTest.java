package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class RemoveRepeatingTransformTest {

    TextTransformerInterface textTransformerInterface = new RemoveRepeatingTransform();

    @Test
    public void shouldRemoveDo() {
        Assertions.assertEquals("Wyślij do mnie wiadomość", textTransformerInterface.transform("Wyślij do do mnie mnie wiadomość"));
    }

    @Test
    public void shouldReturnNotChanged() {
        Assertions.assertEquals("Halko nie potwarzam się", textTransformerInterface.transform("Halko nie potwarzam się"));
    }

    @Test
    public void shouldRemoveAllWordExceptOne() {
        Assertions.assertEquals("halko", textTransformerInterface.transform("halko halko halko halko"));
    }


}