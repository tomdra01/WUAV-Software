package dk.easv.gui.util;

// imports
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

// java imports
import java.util.Locale;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ClockUtil {
    public static void showWidget(HBox hbox){
        Tile clockTile = TileBuilder.create()
                .skinType(Tile.SkinType.CLOCK)
                .dateVisible(true)
                .locale(Locale.UK)
                .running(true)
                .build();

        hbox.getChildren().addAll(clockTile);
        HBox.setMargin(clockTile, new Insets(-5, 0, 0, 1000));
    }
}
