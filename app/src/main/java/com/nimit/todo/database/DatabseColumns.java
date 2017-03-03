package com.nimit.todo.database;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

public class DatabseColumns {
    @DataType(DataType.Type.INTEGER)
    @NotNull
    @PrimaryKey
    @AutoIncrement
    public static final String ID = "_id";

    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String ITEM = "item";

    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String DESCRIPTION = "description";

    @DataType(DataType.Type.INTEGER)
    @NotNull
    public static final String PRIORITY = "priority";

    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String DUE = "due";

}
