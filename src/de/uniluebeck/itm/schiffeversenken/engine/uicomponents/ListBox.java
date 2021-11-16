package de.uniluebeck.itm.schiffeversenken.engine.uicomponents;

import de.uniluebeck.itm.schiffeversenken.engine.Application;
import de.uniluebeck.itm.schiffeversenken.engine.Canvas;
import de.uniluebeck.itm.schiffeversenken.engine.Vec2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class ListBox<E> extends Component implements Drawable, Clickable {

    private final List<E> items;
    private final Callable<E> plusButtonCallable;
    private final Consumer<E> editButtonConsumer;

    private Vec2 latestClick;
    private int rowScrollPositionOffset;

    /**
     * Use this constructor in order to generate a new ListBox.
     * @param x The x position of the list box.
     * @param y The y position of the list box.
     * @param width The width of the list box.
     * @param height The height of the list box.
     * @param items The item list to use. It might be smart to use an array list.
     * @param plusActionCallable A callable that will be triggered when the plus button was pressed. It needs to return
     *                           a new entity of E that will be added to the items list. Keep in mind that this
     *                           operation will run on the graphics thread so you can't introduce calls that require a
     *                           long time to finish. The component won't be locked for new button presses while doing
     *                           so.
     * @param editActionConsumer This consumer will be called with the item to edit if the user wishes to do so.
     */
    public ListBox(int x, int y, int width, int height, List<E> items, Callable<E> plusActionCallable, Consumer<E> editActionConsumer) {
        super();
        if (items != null)
            this.items = items;
        else
            this.items = new ArrayList<E>();
        this.setPosition(new Vec2(x, y));
        this.setWidth(width);
        this.setHeight(height);
        this.plusButtonCallable = plusActionCallable;
        this.editButtonConsumer = editActionConsumer;
        this.rowScrollPositionOffset = 0;
    }

    @Override
    public boolean checkedIfClicked(Vec2 clickPosition) {
        final int clickX = clickPosition.getX();
        final int clickY = clickPosition.getY();
        final int x = this.getPosition().getX();
        final int y = this.getPosition().getY();
        final int height = this.getHeight();
        this.latestClick = clickPosition;
        return this.isEnabled() && ((clickX >= x + 2
                && clickX <= x + 50
                && clickY >= y + 2
                && clickY <= y + height - 25)
                || (clickX >= x + 2
                && clickX <= x + 27
                && clickY >= y + height - 25
                && clickY <= y + height - 2));
    }

    @Override
    public void performAction() {
        final int x = this.getPosition().getX();
        final int y = this.getPosition().getY();
        final int clickX = this.latestClick.getX() - x;
        final int clickY = this.latestClick.getY() - y;
        final int height = this.getHeight();
        if (clickX >= 2
            && clickX <= 27
            && clickY >= height - 25
            && clickY <= height - 2) {
            // We pressed the plus button
            if(this.plusButtonCallable != null) {
                try {
                    this.items.add(this.plusButtonCallable.call());
                } catch (Exception e) {
                    Application.crash(e);
                }
            }
        }
        if (clickX >= 2
                && clickX <= 50
                && clickY >= 2
                && clickY <= height - 25
        && (clickY - 2) % 25 > 2
        && (clickY - 2) % 25 < 24) {
            // The user clicked on an item button. Lets check which one.
            final int itemIndex = rowScrollPositionOffset + (clickY - 2) / 25;
            if (clickX >= 2 && clickX <= 32) {
                // The user clicked on edit
                this.editButtonConsumer.accept(this.items.get(itemIndex));
            }
            if (clickX >= 37 && clickX <= 50) {
                // The user clicked on remove
                this.items.remove(itemIndex);
            }
        }
    }

    @Override
    public void draw(Canvas c, Vec2 mouseLocation) {
        c.setColor(0.7, 0.7, 0.7);

        final int posX = this.getPosition().getX();
        final int posY = this.getPosition().getY();

        final int width = this.getWidth();
        final int height = this.getHeight();

        c.drawRoundRect(posX, posY, width, height, 2, 2);
        for(int y = posY + 2, i = rowScrollPositionOffset; i < this.items.size() && y < posY + height - 25; i++) {
            c.setColor(0.7, 0.7, 0.7);
            c.fillRect(posX + 2, y + 2, 30, 25 - 2 * 2);
            c.fillRect(posX + 2 + 35, y + 2, 15, 25 - 2 * 2);
            c.drawString(posX + 2 + 50 + 5, y + 12, this.items.get(i).toString());
            c.setColor(0, 0,0);
            c.drawString(posX + 2, y + 12, "Edit");
            c.drawString(posX + 2 + 35 + 2, y + 12, "-");
            y += 25;
        }

        c.setColor(0.7, 0.7, 0.7);
        c.drawLine(new Vec2(posX, posY + height - 25), new Vec2(posX + width, posY + height - 25));
        c.fillRect(posX + 2, posY + height - 25 + 2, 25, 25 - 2 * 2);
        c.setColor(0, 0, 0);
        c.drawString(posX + 4, posY + height - 20, "+");
    }

    /**
     * Use this method in order to retrieve the items within this list.
     * @return The items as a list of E
     */
    public List<E> getItems() {
        return this.items;
    }
}
