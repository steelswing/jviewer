// Copyright (c) 2011 Chan Wai Shing
//
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

package syntaxhighlighter.brush;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Java brush.
 *
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushPreudoC extends Brush {

    public BrushPreudoC() {
        super();

        String keywords1 = "__LINE__ __VERSION__ __FILE__ attribute varying uniform buffer highp mediump lowp smooth flat perspective noperspective subroutine void bool int uint float double atomic_uint bvec2 bvec3 bvec4 ivec2 ivec3 ivec4 vec2 vec3 vec4 uvec2 uvec3 uvec4 dvec2 dvec3 dvec4 mat2 mat2x2 mat3 mat3x3 mat4 mat4x4 mat2x3 mat2x4 mat3x2 mat3x4 mat4x2 mat4x3 sampler1D sampler2D sampler3D samplerCube sampler1DShadow sampler2DShadow sampler2DRect sampler2DRectShadow sampler1DArray sampler2DArray sampler1DArrayShadow sampler2DArrayShadow samplerBuffer sampler2DMS sampler2DMSArray isampler1D isampler2D isampler3D isamplerCube isampler2DRect isampler1DArray isampler2DArray isamplerBuffer isampler2DMS isampler2DMSArray usampler1D usampler2D usampler3D usamplerCube usampler2DRect usampler1DArray usampler2DArray usamplerBuffer usampler2DMS usampler2DMSArray image2D iimage2D uimage2D image3D iimage3D uimage3D image2DRect iimage2DRect uimage2DRect imageCube iimageCube uimageCube imageBuffer iimageBuffer uimageBuffer image1DArray iimage1DArray uimage1DArray image2DArray iimage2DArray uimage2DArray imageCubeArray iimageCubeArray uimageCubeArray image2DMS iimage2DMS uimage2DMS image2DMSArray iimage2DMSArray uimage2DMSArray struct const return true false break continue if else elseif switch case default while for do until discard";

        String keywords2 = "sample patch centroid volatile restrict readonly writeonly precise coherent layout invariant interpolation storage precision in out inout compatibility require enable warn disable #inject #include #require # #define #undef #if #ifdef #ifndef #else #elif #endif #error #pragma #extension #version #line 0 1 2 3 4 5 6 7 8 9 .";

        String keywords3 = "gl_ModelViewProjectionMatrix gl_ModelViewMatrix gl_PerVertex gl_MultiTexCoord0 gl_MultiTexCoord1 gl_MultiTexCoord2 gl_MultiTexCoord3 gl_MultiTexCoord4 gl_MultiTexCoord5 gl_MultiTexCoord6 gl_MultiTexCoord7 gl_Color gl_SecondaryColor gl_Normal gl_Vertex gl_MultiTexCoord gl_FogCoord gl_ClipVertex gl_FrontColor gl_BackColor gl_FrontSecondaryColor gl_BackSecondaryColor gl_TexCoord gl_FogFragCoord gl_FragColor gl_FragData gl_ClipDistance gl_FragCoord gl_FragDepth gl_FrontFacing gl_GlobalInvocationID gl_InstanceID gl_InvocationID gl_Layer gl_LocalInvocationID gl_LocalInvocationIndex gl_NumSamples gl_NumWorkGroups gl_PatchVerticesIn gl_PointCoord gl_PointSize gl_Position gl_PrimitiveID gl_PrimitiveIDIn gl_SampleID gl_SampleMask gl_SampleMaskIn gl_SamplePosition gl_TessCoord gl_TessLevelInner gl_TessLevelOuter gl_VertexID gl_ViewportIndex gl_WorkGroupID gl_WorkGroupSize gd_matModel gd_matModelView gd_matModelViewProjection gd_matView gd_matViewProjection gd_matProjection gd_matInverseModel gd_matInverseModelView gd_fltNearPlane gd_fltFarPlane gd_vecEyeOrientationWS gd_vecEyePositionWS gd_InVertex gd_InNormal gd_InVertexColor gd_InTangent gd_InBitangent gd_InUVDiffuse gd_InUVLightmap gd_InUVDetail gd_InColor gd_OutVertex gd_OutFragDiffuse gd_OutFragDepth gd_OutFragNormal gd_OutFragMask";

        String keywords4 = "radians degrees sin cos tan asin acos atan sinh cosh tanh asinh acosh atanh pow exp log exp2 log2 sqrt inversesqrt abs sign floor trunc round roundEven ceil fract mod modf min max clamp mix step smoothstep isnan isinf floatBitsToInt floatBitsToUint intBitsToFloat uintBitsToFloat fma frexp ldexp packUnorm2x16 packUnorm2x16 packUnorm4x8 packSnorm4x8 unpackUnorm2x16 unpackSnorm2x16 unpackUnorm4x8 unpackSnorm4x8 packDouble2x32 unpackDouble2x32 packHalf2x16 unpackHalf2x16 length distance dot cross normalize ftransform faceforward reflect refract matrixCompMult outerProduct transpose determinant inverse lessThan lessThanEqual greaterThan greaterThanEqual equal notEqual any all not uaddCarry usubBorrow umulExtended imulExtended bitfieldExtract bitfieldExtract bitfieldInsert bitfieldInsert bitfieldReverse bitCount findLSB findMSB textureSize textureQueryLod textureQueryLevels texture textureProj textureLod textureOffset texelFetch texelFetchOffset textureProjOffset textureLodOffset textureProjLod textureProjLodOffset textureGrad textureGradOffset textureProjGrad textureProjGradOffset textureGather textureGatherOffset textureGatherOffsets texture1D texture1DProj texture1DLod texture1DProjLod texture2D texture2DProj texture2DLod texture2DProjLod texture3D texture3DProj texture3DLod texture3DProjLod textureCube textureCubeLod shadow1D shadow2D shadow1DProj shadow2DProj shadow1DLod shadow2DLod shadow1DProjLod shadow2DProjLod atomicCounterIncrement atomicCounterDecrement atomicCounter atomicAdd atomicMin atomicMax atomicAnd atomicOr atomicXor atomicExchange atomicCompSwap imageSize imageLoad imageStore imageAtomicAdd imageAtomicMin imageAtomicMax imageAtomicAnd imageAtomicOr imageAtomicXor imageAtomicExchange imageAtomicCompSwap dFdx dFdy fwidth interpolateAtCentroid interpolateAtSample interpolateAtOffset noise1 noise2 noise3 noise4 EmitStreamVertex EndStreamPrimitive EmitVertex EndPrimitive memoryBarrier memoryBarrierAtomicCounter memoryBarrierBuffer memoryBarrierShared memoryBarrierImage groupMemoryBarrier gdLinearizeDepthFromPosition gdProjectLinear gdProjectParaboloid gdBRDFPhong gdBRDFBlinnPhong gdBRDFWardIsotropic gdBRDFWardAnisotropic gdBRDFMinnaert gdBRDFOrenNayar gdBRDFSimple";

        String keywords5 = "shared packed std140 std430 row_major column_major binding offset align location component index triangles quads isolines equal_spacing fractional_even_spacing fractional_odd_spacing point_mode points lines lines_adjacency triangles_adjacency invocations origin_upper_left pixel_center_integer early_fragment_tests local_size_x local_size_y local_size_z xfb_buffer xfb_stride xfb_offset vertices line_strip triangle_strip max_vertices stream depth_any depth_greater depth_less depth_unchanged rgba32f rgba16f rg32f rg16f r11f_g11f_b10f r32f r16f rgba16 rgb10_a2 rgba8 rg16 rg8 r16 r8 rgba16_snorm rgba8_snorm rg16_snorm rg8_snorm r16_snorm r8_snorm rgba32i rgba16i rgba8i rg32i rg16i rg8i r32i r16i r8i rgba32ui rgba16ui rgb10_a2ui rgba8ui rg32ui rg16ui rg8ui r32ui r16ui r8ui ";

        List<RegExpRule> _regExpRuleList = new ArrayList<RegExpRule>();
        _regExpRuleList.add(new RegExpRule(RegExpRule.singleLineCComments, "comments")); // one line comments
        _regExpRuleList.add(new RegExpRule("\\/\\*([^\\*][\\s\\S]*?)?\\*\\/", Pattern.MULTILINE, "comments")); // multiline comments
        _regExpRuleList.add(new RegExpRule("\\/\\*(?!\\*\\/)\\*[\\s\\S]*?\\*\\/", Pattern.MULTILINE, "preprocessor")); // documentation comments
        _regExpRuleList.add(new RegExpRule(RegExpRule.doubleQuotedString, "string")); // strings
        _regExpRuleList.add(new RegExpRule(RegExpRule.singleQuotedString, "string")); // strings
        _regExpRuleList.add(new RegExpRule("\\b([\\d]+(\\.[\\d]+)?|0x[a-f0-9]+)\\b", Pattern.CASE_INSENSITIVE, "value")); // numbers
        _regExpRuleList.add(new RegExpRule("^ *#.*", Pattern.MULTILINE, "preprocessor"));
        _regExpRuleList.add(new RegExpRule(getKeywords(keywords1), Pattern.MULTILINE, "keyword")); // java keyword
        _regExpRuleList.add(new RegExpRule(getKeywords(keywords2), Pattern.MULTILINE, "keyword")); // java keyword
        _regExpRuleList.add(new RegExpRule(getKeywords(keywords4), Pattern.MULTILINE, "functions")); // java keyword

        _regExpRuleList.add(new RegExpRule(getKeywords(keywords5), Pattern.MULTILINE, "color1")); // java keyword
        
        
        
        _regExpRuleList.add(new RegExpRule(getKeywords(keywords3), Pattern.MULTILINE, "constants")); // java keyword
        
        setRegExpRuleList(_regExpRuleList);

//        setHTMLScriptRegExp(new HTMLScriptRegExp("(?:&lt;|<)%[@!=]?", "%(?:&gt;|>)"));

        setCommonFileExtensionList(Arrays.asList("cpp"));
    }
}
