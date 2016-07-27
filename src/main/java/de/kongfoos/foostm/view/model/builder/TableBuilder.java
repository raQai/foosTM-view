package de.kongfoos.foostm.view.model.builder;

import de.kongfoos.foostm.model.table.TableImpl;
import de.kongfoos.foostm.model.table.TableType;

import javax.validation.constraints.NotNull;

public abstract class TableBuilder<T extends TableImpl> {

    private final int id;
    private final TableType type;

    protected TableBuilder(@NotNull int id, @NotNull TableType type) {
        this.id = id;
        this.type = type;
    }

    protected abstract T getInstance();

    public T build() {
        final T table = getInstance();
        table.setID(id);
        table.setType(type);
        return table;
    }
}
