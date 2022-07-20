package net.fabricmc.MCHacks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.MCHacks.MCHacks;
import net.fabricmc.MCHacks.MCHacksClient;
import net.minecraft.block.AbstractBlock.AbstractBlockState;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class XRayHackMixin{

    @Mixin(AbstractBlockState.class)
    public static class AbstractBlockStateMixin{
        @Inject(at = @At("HEAD"), method = "isOpaque", cancellable = true)
        private void isOpaque(CallbackInfoReturnable<Boolean> info) {
            if(MCHacks.xray.isEnabled() && !MCHacksClient.oreBlocks.contains(((AbstractBlockState)(Object)this).getBlock())){
                info.setReturnValue(false);
                info.cancel();
            }
	    }

        @Inject(at = @At("HEAD"), method = "getLuminance", cancellable = true)
        private void getLuminance(CallbackInfoReturnable<Integer> info) {
            if(MCHacks.xray.isEnabled() && MCHacksClient.oreBlocks.contains(((AbstractBlockState)(Object)this).getBlock())){
                info.setReturnValue(15);
                info.cancel();
            }
	    }
    }

    @Mixin(Block.class)
    public static class BlockMixin{
        @Inject(at = @At("HEAD"), method = "shouldDrawSide", cancellable = true)
        private static void shouldDrawSide(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, Direction direction_1, BlockPos blockPos_2, CallbackInfoReturnable<Boolean> info) {
            if(MCHacks.xray.isEnabled() && !MCHacksClient.oreBlocks.contains(blockState_1.getBlock())){
                info.setReturnValue(false);
                info.cancel();
            }
	    }
    }
	//public static Block[] oreBlocks = {Blocks.IRON_ORE, Blocks.COAL_ORE, Blocks.REDSTONE_ORE, Blocks.LAPIS_ORE, Blocks.GOLD_ORE, Blocks.DIAMOND_ORE};
    //public static ArrayList<Block> oreBlocks = new ArrayList<Block>();

	// public XRayHack(String name, String description) {
	// 	super("XRayHack", "See through blocks");
	// 	//TODO Auto-generated constructor stub
	// }

	

	// public static boolean shouldDrawSide(BlockState state, BlockView world, BlockPos pos, Direction side, BlockPos otherPos) {
    //     BlockState blockState = world.getBlockState(otherPos);
    //     if (state.isSideInvisible(blockState, side)) {
    //         return false;
    //     }
    //     if (blockState.isOpaque()) {
    //         NeighborGroup neighborGroup = new NeighborGroup(state, blockState, side);
    //         Object2ByteLinkedOpenHashMap<NeighborGroup> object2ByteLinkedOpenHashMap = FACE_CULL_MAP.get();
    //         byte b = object2ByteLinkedOpenHashMap.getAndMoveToFirst(neighborGroup);
    //         if (b != 127) {
    //             return b != 0;
    //         }
    //         VoxelShape voxelShape = state.getCullingFace(world, pos, side);
    //         if (voxelShape.isEmpty()) {
    //             return true;
    //         }
    //         VoxelShape voxelShape2 = blockState.getCullingFace(world, otherPos, side.getOpposite());
    //         boolean bl = VoxelShapes.matchesAnywhere(voxelShape, voxelShape2, BooleanBiFunction.ONLY_FIRST);
    //         if (object2ByteLinkedOpenHashMap.size() == 2048) {
    //             object2ByteLinkedOpenHashMap.removeLastByte();
    //         }
    //         object2ByteLinkedOpenHashMap.putAndMoveToFirst(neighborGroup, (byte)(bl ? 1 : 0));
    //         return bl;
    //     }
    //     return true;
    // }
}
