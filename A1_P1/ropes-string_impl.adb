-----------------------------------------------------------------------
--  Ropes.String_Impl package bpdy
--
--  Author: Chris Reedy (Chris.Reedy@wwu.edu)
-----------------------------------------------------------------------

package body Ropes.String_Impl is

    overriding
    procedure Dispose (R : access Large_String_Impl) is
    begin
        Free (R.S);
   end Dispose;

    overriding
    function Element (Source : Large_String_Impl; Index : Positive)
      return Character is
    begin
        pragma Assert (Index <= Source.S'Length);
        return Source.S (Index);
   end Element;

    overriding
    procedure To_String (Source : access constant Large_String_Impl;
                         Target : in out String; Start : in Positive) is
    begin
        Target (Start .. Start + Source.Length - 1) := Source.S.all;
   end To_String;

    overriding
    function Slice (Source : access Large_String_Impl;
                    Low : Positive; High : Natural)
      return Rope_Impl_Access is
    begin
        pragma Assert (Low <= High and High <= Source.Length);
        if Low = 1 and High = Source.Length then
            Inc_Ref_Count (Source);
            return Rope_Impl_Access (Source);
        end if;
        return Make_String_Impl (Source.S (Low .. High));
   end Slice;

    overriding
    function Impl_Text_Contents (R : access constant Large_String_Impl)
      return String is
    begin
        return " " & R.S.all;
   end Impl_Text_Contents;

   overriding
   procedure Dispose (R : access Small_String_Impl) is
  begin
     	return;
 end Dispose;

   overriding
    function Element (Source : Small_String_Impl; Index : Positive)
      return Character is
    begin
        pragma Assert (Index <= Source.S'Length);
        return Source.S (Index);
   end Element;

   overriding
    procedure To_String (Source : access constant Small_String_Impl;
                         Target : in out String; Start : in Positive) is
    begin
      --Target (Start .. Start + Source.Length - 1) := Source.S(Start .. Start + Source.Length - 1);
      Target (Start .. Start + Source.Length - 1) := Source.S(Source.S'First .. Source.Length);
   end To_String;

    overriding
    function Slice (Source : access Small_String_Impl;
                    Low : Positive; High : Natural)
      return Rope_Impl_Access is
    begin
        pragma Assert (Low <= High and High <= Source.Length);
        if Low = 1 and High = Source.Length then
            Inc_Ref_Count (Source);
            return Rope_Impl_Access (Source);
        end if;
        return Make_String_Impl (Source.S (Low .. High));
    end Slice;

   overriding
    function Impl_Text_Contents (R : access constant Small_String_Impl)
      return String is
    begin
        return " " & R.S;
    end Impl_Text_Contents;

    package body Make is
      function Make_String_Impl (Source : in String) return Rope_Impl_Access is
         Small_Str : String(1 .. Max_Small_String);
      begin
         if Source'Length = 0 then
            return null;
         --  Need to shift the index of Source to be 1 based
         elsif Source'Length <= Max_Small_String then
            	Small_Str(1 .. Source'Length) := Source(Source'First .. Source'Last);
            declare
               Small_Impl : constant Rope_Impl_Access := new Small_String_Impl'(Ref_Count => 0, Length => Source'Length, S => Small_Str(1 .. Max_Small_String));
            begin
                Inc_Ref_Count (Small_Impl);
                return Small_Impl;
            end;

         else
            declare
            	Str : constant String_Access := new String (1 .. Source'Length);
            	Large_Impl : constant Rope_Impl_Access := new Large_String_Impl'(Ref_Count => 0, Length => Source'Length, S => Str);
            begin
               	Str.all := Source;
            	Inc_Ref_Count (Large_Impl);
               	return Large_Impl;
            end;
         end if;
        end Make_String_Impl;
    end Make;

end Ropes.String_Impl;
