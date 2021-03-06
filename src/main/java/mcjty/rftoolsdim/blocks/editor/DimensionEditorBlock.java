package mcjty.rftoolsdim.blocks.editor;

import mcjty.lib.api.Infusable;
import mcjty.lib.container.GenericGuiContainer;
import mcjty.rftoolsdim.RFToolsDim;
import mcjty.rftoolsdim.blocks.GenericRFToolsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class DimensionEditorBlock extends GenericRFToolsBlock<DimensionEditorTileEntity, DimensionEditorContainer> implements Infusable {

    public enum OperationType implements IStringSerializable {
        NORMAL("normal"),
        EMPTY("empty"),
        BUILDING1("building1"),
        BUILDING2("building2");

        private final String name;

        OperationType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    public static final PropertyEnum<DimensionEditorBlock.OperationType> OPERATIONTYPE = PropertyEnum.create("operationtype", DimensionEditorBlock.OperationType.class);


    public DimensionEditorBlock() {
        super(Material.IRON, DimensionEditorTileEntity.class, DimensionEditorContainer.class, "dimension_editor", true);
    }

    @Override
    public int getGuiID() {
        return RFToolsDim.GUI_DIMENSION_EDITOR;
    }

    @Override
    public Class<? extends GenericGuiContainer> getGuiClass() {
        return GuiDimensionEditor.class;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> list, boolean whatIsThis) {
        super.addInformation(itemStack, player, list, whatIsThis);

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            list.add(TextFormatting.WHITE + "This machine allows you to inject certain types");
            list.add(TextFormatting.WHITE + "of dimlets into an existing dimension. This cannot");
            list.add(TextFormatting.WHITE + "be undone and the dimlet is lost so be careful!");
            list.add(TextFormatting.YELLOW + "Infusing bonus: reduced power consumption.");
        } else {
            list.add(TextFormatting.WHITE + RFToolsDim.SHIFT_MESSAGE);
        }
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        DimensionEditorTileEntity te = (DimensionEditorTileEntity) world.getTileEntity(pos);
        return state.withProperty(OPERATIONTYPE, te.getState());
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, OPERATIONTYPE);
    }
}
